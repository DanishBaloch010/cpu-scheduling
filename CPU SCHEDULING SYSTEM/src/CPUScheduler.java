
import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduler
{
    private final List<Row> rows;
    private final List<Event> timeline;
    private int timeQuantum;
    
    public CPUScheduler()
    {
        rows = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;
    }
    
    public boolean add(Row row)
    {
        // oper bane we list me row ko add kardo.
        return rows.add(row);
    }
    
    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }
    
    public int getTimeQuantum()
    {
        return timeQuantum;
    }
    
    public  double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}

    
    public double getAverageWaitingTime()
    {
        double avg = 0.0;
        
        for (Row row : rows)
        {
            avg += row.getWaitingTime();
        }
       // return  avg / rows.size();
       return round(avg / rows.size(),2);
    }
    
    public double getAverageTurnAroundTime()
    {
        double avg = 0.0;
        
        for (Row row : rows)
        {
            avg += row.getTurnaroundTime();
        }
        
        return round(avg / rows.size(),2);
       // return avg / rows.size();
    }
    
    public Event getEvent(Row row)
    {
        for (Event event : timeline)
        {
            if (row.getProcessName().equals(event.getProcessName()))
            {
                return event;
            }
        }
        
        return null;
    }
    
    public Row getRow(String process)
    {
        for (Row row : rows)
        {
            if (row.getProcessName().equals(process))
            {
                return row;
            }
        }
        
        return null;
    }
    
    public List<Row> getRows()
    {
        // oper bane we row list ko return karo.
        // jisme me process hai.
        return rows;
    }
    
    public List<Event> getTimeline()
    {
        return timeline;
    }
    
    // yeh abstract method hai jo har algo apne hisaab se implement karega.
    public abstract void process();
}
