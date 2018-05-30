package com.example.dataacqdemo.netty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParamsPool {

    private Map<String, Long> paramsMap;

    public ParamsPool() {
        this.paramsMap = new HashMap<>();
        new ClearThread(paramsMap).start();
    }

    public boolean add(String param){
        if(paramsMap.containsKey(param)){
            return false;
        }
        paramsMap.put(param, System.currentTimeMillis());
        return true;
    }

    class ClearThread extends Thread{

        private Map<String, Long> paramsMap;

        public ClearThread(Map<String, Long> paramsMap) {
            this.paramsMap = paramsMap;
        }

        @Override
        public void run() {
            while (true){
                if(paramsMap != null && !paramsMap.isEmpty()){
                    Iterator<Map.Entry<String, Long>> iterator = paramsMap.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, Long> next = iterator.next();
                        if(System.currentTimeMillis() - next.getValue() > 5000){
                            iterator.remove();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
