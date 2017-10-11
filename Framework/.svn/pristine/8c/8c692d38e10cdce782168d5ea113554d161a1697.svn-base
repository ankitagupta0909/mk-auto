package com.markit.framework.kyc.pageMethods;


import java.util.LinkedList;

public class EntityIdGenerator {

    private static final long BASE = 14000000000000000L;
    private static final int MAX_SIZE = 10000;
    private LinkedList<Long> queue = new LinkedList<Long>();
    
      /**
       * The <code>generate</code> method generates a unique id based on the
       * current time stamp and a random number.
       * 
       * @return <code>long</code> value representing unique id.
       */
    public synchronized long generate() {

           Long candidate = null;
           
           while (true) {
                  
                  candidate = System.currentTimeMillis() * 10000L
                               + (long) (Math.random() * 9000 + 1000) - BASE;
                  
                  if (queue.contains(candidate)) continue; 
                  
                  if (queue.size() >= MAX_SIZE) queue.remove();
                  
                  queue.add(candidate);
                  
                  return candidate;
           }
           
    }
}
