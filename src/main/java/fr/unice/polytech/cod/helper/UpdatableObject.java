package fr.unice.polytech.cod.helper;

import static java.lang.Thread.sleep;

/**
 * Updatable object is a class that should be extended by your own class if a timer is needed.
 * - Use the super() parameter to configure the waiting time (in ms) of your timer.
 * - Override the OnTimeReached() to set up what should be done if the timer is reached.
 * - Call the start() method to start the timer.
 */
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
    public void startTimer(){
        killCurrentThread();
        currentThread = new Thread(this);
        currentThread.start();
    }

    /**
     * Kill the current thread
     */
    public void killCurrentThread(){
        if(currentThread != null)
            currentThread.interrupt();
    }

    /**
     * Be sure to know exactly what you are doing before calling this method.
     * Maybe you want to call start() method instead.
     */
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
