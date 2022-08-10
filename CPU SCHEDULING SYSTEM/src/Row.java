
public class Row
{
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int waitingTime;
    private int turnaroundTime;
    private int priority;
    
    // initializing all the private variable of a process Row
    private Row(String processName, int arrivalTime, int burstTime, int priority, int waitingTime, int turnaroundTime)
    {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
    }
    
    // this one is basically used for setting the values. 
    // thats why i made it public.
    public Row(String processName, int arrivalTime, int burstTime, int priority)
    {
        this(processName, arrivalTime, burstTime, priority, 0,0);
    }
    
    public void setBurstTime(int burstTime)
    {
        this.burstTime = burstTime;
    }
    
    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }
    
    public void setTurnaroundTime(int turnaroundTime)
    {
        this.turnaroundTime = turnaroundTime;
    }
    
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    
    public String getProcessName()
    {
        return this.processName;
    }
    
    public int getArrivalTime()
    {
        return this.arrivalTime;
    }
    
    public int getBurstTime()
    {
        return this.burstTime;
    }
    
    public int getPriority()
    {
        return this.priority;
    }
    
    public int getWaitingTime()
    {
        return this.waitingTime;
    }
    
    public int getTurnaroundTime()
    {
        return this.turnaroundTime;
    }
}
