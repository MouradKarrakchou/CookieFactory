package fr.unice.polytech.cod;

public class TimeClock implements Comparable{
    private int hour;
    private int minute;


    public TimeClock(int hour,int minute){
        //for the case where minute >=60
        int hourToAdd=minute/60;
        int realminute=minute%60;
        this.hour=hourToAdd+hour;
        this.minute=realminute;
    }

    /**
     * compare this with timeClock+the numberOfMinutes that we want to add
     * @param timeClock
     * @param numberOfMinutes
     * @return
     */
    public int compareWithMinuteAdded(TimeClock timeClock,int numberOfMinutes){
        TimeClock timeClock1=new TimeClock(timeClock.hour,timeClock.minute+numberOfMinutes);
        return(this.compareTo(timeClock));
    }
    public TimeClock timeClock15MinuteLater(){
        return(new TimeClock(hour,minute+15));
    }

    /**
     * return the time difference between this and timeClock in minutes(always positive)
     * @param timeClock
     * @return
     */
    public int timeDifference(TimeClock timeClock){
        int minuteTotal=timeClock.hour*60+timeClock.minute;
        int thisMinuteTotal=this.hour*60+this.minute;
        return(Math.abs(thisMinuteTotal-minuteTotal));
    }


    @Override
    public int compareTo(Object o) {
        TimeClock timeClock=(TimeClock)o;
        if (timeClock.hour<this.hour) return 1;
        else if (timeClock.hour>this.hour) return -1;
        else if (timeClock.minute<this.minute) return 1;
        else if (timeClock.minute>this.minute) return -1;
        else return 0;
    }
}
