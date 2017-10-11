'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'''''''''''''''''''''''''''''''''''''''''''''''''''''Create Excel Object , Read TestCase Name , Read Result'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Set myxl = CreateObject("excel.application")

myxl.Workbooks.Open "C:\\AutomationWorkspace\\Framework\\src\\testdata\\KYCmappingSheet.xlsx"

myxl.Application.Visible = true
Set mysheet = myxl.ActiveWorkbook.Worksheets("FinalTestStatus")

row=mysheet.UsedRange.Rows.Count

Col=mysheet.UsedRange.columns.count

'''Reading the header when row =1 


max=100
min=1
Randomize
RandomStr=(Int((max-min+1)*Rnd+min))

'RandomStr= GenerateRandomString(3)
For j=1 to col

	headertxt = mysheet.cells(1,j).Value
		If headertxt = "ALMTestCaseName" Then
			TestCaseNameCol =j
		End If
	
			If headertxt = "TestCaseStatus" Then
				ResultCol =j
		End if
	
Next
Set dict = CreateObject("Scripting.Dictionary")
''''''''Getting the test cases in an array
	For i=2 to row
		TestNameVal= mysheet.cells(i,TestCaseNameCol)
		StatusVal=mysheet.cells(i,ResultCol)
		dict.Add TestNameVal,StatusVal
		Next

myxl.ActiveWorkbook.Save
myxl.ActiveWorkbook.Close
   myxl.Application.Quit

Set mysheet =nothing
	Set myxl=nothing


qcUser = "ankita.gupta"
qcPassword = "Markit1234"
qcDomain = "SOLUTIONS"
qcProject = "KYC"

    qcServer = "http://alm.markit.partners"
    qcServer = qcServer & "/qcbin"
	
    Set QCConnection = CreateObject("tdapiole80.tdconnection")
    
    If (QCConnection Is Nothing) Then
        msgbox "QCConnection object is empty"
    End If
    
    QCConnection.InitConnectionEx qcServer
    QCConnection.Login qcUser, qcPassword
    QCConnection.Connect qcDomain, qcProject

    Set QCTreeManager=QCConnection.TreeManager
    Set TestNode=QCTreeManager.nodebypath("Subject\WFM\WFM 1.0\User Roles")
	Set TSetFact =QCConnection.TestSetFactory
    Set TestFact = TestNode.TestFactory
    Set TestsList = TestFact.NewList("")
    
    
    Set QCTSTreeManager = QCConnection.TestSetTreeManager  
	Set tcTreeMgr = QCConnection.TreeManager   
    Set  TreeNode=QCTSTreeManager.NodebyPath("Root\KYC Pega WFM\WFM 1.0")
    Set TestSetFact=TreeNode.TestSetFactory

Set subjectfldr = tcTreeMgr.NodebyPath("Subject")

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
''''''''''''''''''''''''''''''''''''''''''''''''''ADDING ATEST CASE TO ALM'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' read the main and sub folder names
 folder = "KYCAutomation"
 subfolder = "AutomationCases"&RandomStr

On Error Resume Next
' create main folder

Set subjectfldr = subjectfldr.AddNode(folder)
subjectfldr.Post

If Err.Number <> 0 Then ' if main folder does not exists
Set subjectfldr = QCTreeManager.NodebyPath("Subject\" & folder)
End If
'create subfolder if specified
If Not subfolder = "" Then
Set trfolder = subjectfldr.AddNode(subfolder)
trfolder.Post
End If

'reset error handling
 On Error GoTo 0
If subfolder = "" Then
Set trfolder = QCTreeManager.NodebyPath("Subject\" & folder)
Else
Set trfolder = QCTreeManager.NodebyPath("Subject\" & folder & "\" & subfolder)
End If

' now create a test case
testkeys = dict.Keys
For  i=0 to dict.Count-1
Set sampleTest = trfolder.TestFactory.AddItem(Null)
sampleTest.Name = testkeys(i)
' create test steps
Set dsf = sampleTest.DesignStepFactory
Set stepList = dsf.NewList("[empty]")

sampleTest.Field("TS_STATUS")="Ready"
sampleTest.Post
Next
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''Moving the added case to Test LAB and executing'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Set QCTreeManager=QCConnection.TreeManager
    Set TestNode=QCTreeManager.nodebypath("Subject\" & folder & "\" & subfolder)
    Set TestFact = TestNode.TestFactory
    Set TestsList = TestFact.NewList("")
    
    
    Set QCTSTreeManager = QCConnection.TestSetTreeManager     
    Set  TreeNode=QCTSTreeManager.NodebyPath("Root\KYCAutomation")
    Set TestSetFact=TreeNode.TestSetFactory
    
    Set NewTestSet=TestSetFact.AddItem(Null)' Creates new testset
    NewTestSet.name="KYC Automation Suite"&RandomStr
    NewTestSet.Field("CY_COMMENT")=testSetName
    NewTestSet.status="Open"
    NewTestSet.post
	TestSetID = NewTestSet.Field("CY_CYCLE_ID")
    
    Set TSTestFactory=NewTestSet.TSTestFactory
    For Each Tests in TestsList
            TSTestFactory.Additem(tests)
    Next



Set TSetFact = QCConnection.TestSetFactory
Set tstfilter=TSetFact.filter
tstfilter.Filter("CY_CYCLE_ID") = testSetID

Set tsList=TSetFact.Newlist(tstfilter.Text)


Set theTestSet = tsList.Item(1) 


TSName = theTestSet.Name


Set TSTestFact = theTestSet.TSTestFactory     
Set TestSetTestsList = TSTestFact.NewList("") 

On Error Resume Next 
		For each thetest in TestSetTestsList
		
		
		runName = thetest.Name
		
		Set RunF = thetest.RunFactory     
		Set theRun = RunF.addItem(runName)   
					
					testnamekeys = dict.Keys
					teststatusval = dict.Items
					For  i=0 to dict.Count-1
						 If Instr(runName, testkeys(i))>0  then
								theRun.Status = teststatusval(i)
								theRun.Post
								Exit For
						End if
					Next
		Next
 On Error GoTo 0
Set RunF =Nothing
  Set QCConnection = Nothing
'
'Common values of the Status list are:
'
'•NO_RUN - Default. The instance was never executed.
'•NOT_COMPLETED - Execution of the test instance has not been completed.
'•FAILED - The test instance failed during test execution.
'•PASSED - The test instance passed during test execution.
'•N/A - The execution status is not available.
'

Function GenerateRandomString(StrLen)
Dim myStr
Const MainStr= "0123456789"
For i = 1 to StrLen
  myStr=myStr & Mid(MainStr,RandomNumber(1, Len(MainStr)),1)
Next
msgbox mystr
GenerateRandomString = myStr
End Function










