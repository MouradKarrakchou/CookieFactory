package fr.unice.polytech.cod;

public class UpdatableObject extends Thread {
    private int waitingTime;

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
    @Override
    public void run() {
        try {sleep(waitingTime);}
        catch (InterruptedException e) {throw new RuntimeException(e);}
        OnTimeReached();
    }

    /**
     * To override: This method is called one by Run() when the waitingTime is reached.
     */
    protected void OnTimeReached(){}
}
