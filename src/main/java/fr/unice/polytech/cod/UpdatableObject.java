package fr.unice.polytech.cod;

import static java.lang.Thread.sleep;

public class UpdatableObject implements Runnable {
    private int waitingTime;
    private Thread currentThread;

    /**
     * Instantiate a new UpdatableObject
     * @param waitingTime the time (in ms) to wait before calling OnTimeReached method.
     */
    public UpdatableObject(int waitingTime){
        this.waitingTime = waitingTime;
    }

    public void setWaitingTime(int waitingTime){
        this.waitingTime = waitingTime;
    }

    /**
     * This method will wait for waitingTime (in ms) before calling OnTimeReached method.
     */
    public void start(){
        killCurrentThread();
        currentThread = new Thread(this);
        currentThread.start();
    }

    /**
     * Kill the current thread
     */
    private void killCurrentThread(){
        if(currentThread != null)
            currentThread.interrupt();
    }

    @Override
    public void run() {
        try {
            sleep(waitingTime);
            OnTimeReached();
        }
        catch (InterruptedException ignored) {}
    }

    /**
     * To override: This method is called one by Run() when the waitingTime is reached.
     */
    protected void OnTimeReached(){}
}
