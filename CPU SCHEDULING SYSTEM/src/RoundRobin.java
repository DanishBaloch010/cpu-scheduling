
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRobin extends CPUScheduler
{
    @Override
    public void process()
    {
         // Sorting incoming processes on the basis of arrival time //
        
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        /* 
        
        Deep copy is required because we will be splitting each process
        according to time quantum and save respected lists.
        
        */
        List<Row> rows = Utility.deepCopy(this.getRows());
        int time = rows.get(0).getArrivalTime();
        int timeQuantum = this.getTimeQuantum();
        
        while (!rows.isEmpty())
        {
            Row row = rows.get(0);
            
            // If burst time is less than time quantum, execute the whole process.
            
            int bt = (row.getBurstTime() < timeQuantum ? row.getBurstTime() : timeQuantum);
            this.getTimeline().add(new Event(row.getProcessName(), time, time + bt));
            time += bt;
            rows.remove(0);
            
            if (row.getBurstTime() > timeQuantum)
            {
                row.setBurstTime(row.getBurstTime() - timeQuantum);
                
                for (int i = 0; i < rows.size(); i++)
                {
                    if (rows.get(i).getArrivalTime() > time)
                    {
                        // Saving with index value in List
                        
                        rows.add(i, row);
                        break;
                    }
                    
                    // For last row
                    
                    else if (i == rows.size() - 1)
                    {
                        rows.add(row);
                        break;
                    }
                }
            }
        }
        
        Map map = new HashMap();
        
        for (Row row : this.getRows())
        {
            // clearing garbage values, incase map is referenced somewhere else.
            map.clear();
            
            for (Event event : this.getTimeline())
            {
                /*
                
                When process is first encountered, all else conditions will apply.
                When we will traverse again, if condition will be appliacble.
                
                */
                if (event.getProcessName().equals(row.getProcessName()))
                {
                    if (map.containsKey(event.getProcessName()))
                    {
                        int w = event.getStartTime() - (int) map.get(event.getProcessName());
                        row.setWaitingTime(row.getWaitingTime() + w);
                    }
                    else
                    {
                        row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
                    }
                    
                    map.put(event.getProcessName(), event.getFinishTime());
                }
            }
            
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}
