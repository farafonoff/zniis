package tk.farafonoff.zniis.parser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class NumberUpdater {
    GenericObjectPool<Grabber> grabbersPool = new GenericObjectPool<Grabber>(new GrabbersFactory());
    ExecutorService executor = Executors.newCachedThreadPool();
    public NumberUpdater() {
        grabbersPool.setMinIdle(3);
        grabbersPool.setMaxActive(-1);
    }
    
    static class GrabbersFactory extends BasePoolableObjectFactory<Grabber> {

        @Override
        public Grabber makeObject() throws Exception {
            return new Grabber();
        }
        
    }
    
    public void initiateUpdate(String number) {
        if (number.length()<10) {
            System.out.println("ignoring "+number);
            return ;
        }
        
        while (number.length()>10) {
            number = number.substring(1); 
        }
        
        final String canonicalNumber = number;
        
        executor.execute(new Runnable() {
            
            public void run() {
                Grabber grb;
                try {
                    synchronized (grabbersPool) {
                        grb = grabbersPool.borrowObject();                        
                    }
                    
                    System.out.println(Thread.currentThread().getName()+" "+grb);
                    try {
                        Operator op = grb.getOperator(canonicalNumber);
                        System.out.println(op);
                    } finally {
                        synchronized (grabbersPool) {
                            grabbersPool.returnObject(grb);                        
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
