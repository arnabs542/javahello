package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;



public class MeetingRoomsII {
	// much faster than other methods.
	public int minMeetingRooms(Interval[] intervals) {
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int rooms = 0;
		int endItr = 0;
		for (int i = 0; i < starts.length; i++) {
			if (starts[i] < ends[endItr]) {
				rooms++;
			} else {
				endItr++;
			}
		}
		return rooms;
	}

}



class MeetingRoomsII_heap {
	private static final int START = 0; 
	private static final int END = 1;
	
	private class Event {
		int time;
		int type; // end event is 1, start -- 1
		
		public Event(int time, int type) {
			this.time = time;
			this.type = type;
		}
	}
	
	public int minMeetingRooms(Interval[] intervals) {
		int rooms = 0;
		int max_rooms = 0;
		
		// initialize an event queue based on event's happening time
		Queue<Event> events = new PriorityQueue<>(new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				// sort: 1. time ascending, 2. type, end before start
				if (e1.time == e2.time) {
					return e2.type - e1.type;
				} else {
					return e1.time - e2.time;
				}
			}
		});
		
		// create event and push into event queue
		for (Interval interval : intervals) {
			events.offer(new Event(interval.start, START));
			events.offer(new Event(interval.end, END));
		}
		// process events and keep tracking the max_rooms
		while (!events.isEmpty()) {
			Event cur = events.poll();
			if (cur.type == START) {
				rooms++;
				max_rooms = Math.max(max_rooms, rooms);
			} else {
				rooms--;
			}
		}
		return max_rooms;
	}
	
	
	// another way to solve in heap, code is clean, but runs slow, 9%
	// basic idea, 
	// use heap as a resource pool,
	// when need new resource (no existing resource can be released and reuse), put into pool; 
	// when there is a resource can be reused (meeting x ends earlier than meeting y), poll from pool (consumed)
	// and this new room by meeting y will be put to pool (
	// at the end, the pool shows how many resources are used.
	
    public int minMeetingRooms_heap(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals,(a,b)->a.start-b.start);
        PriorityQueue<Interval> pq = new PriorityQueue<>((a,b)->a.end-b.end);
        pq.add(intervals[0]);
        for(int i=1; i<intervals.length; i++) {
            if(intervals[i].start >= pq.peek().end) {
                pq.poll();
            }
            pq.offer(intervals[i]);
        }
        return pq.size();
    }
}


class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }	
}

class MeetingRoomsII_self {
	
	/**
	 * Definition for an interval.
	 * public class Interval {
	 *     int start;
	 *     int end;
	 *     Interval() { start = 0; end = 0; }
	 *     Interval(int s, int e) { start = s; end = e; }
	 * }
	 */
	 private class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }	
	 }
	    /**
	    
	    Basic idea: 
	    (1) parse the intervals into tuple (time, open/close), sort according to (1: time, 2: close > open)-> event list
	    (2) loop through the event list,
	            2.1 when open, push to stack (keep a max_room according to current size of stack)
	            2.2 when close, pop on from stack
	    (3) then the result is the max_room        
	    
	    */
	    
	    private static class Event implements Comparable<Event> {
	        public int time;
	        public boolean start;
	        public Event(int time, boolean start) {
	            this.time = time;
	            this.start = start;
	        }
	        
	        @Override
	        public int compareTo(Event other) {
	            if (this.time == other.time) {
	                if (this.start == other.start) 
	                    return 0;
	                else {
	                    return !this.start && other.start ? -1 : 1;  //!!! carefule here, close goes before open
	                 }
	            } else {
	                return this.time < other.time ? -1 : 1;
	            }
	        }
	        
	    }
	        
	    public int minMeetingRooms(Interval[] intervals) {
	        if (intervals == null || intervals.length == 0) {
	            return 0;
	        }
	        int result = 0;
	        // parse the intervals into event list
	        List<Event> events = getEventList(intervals);
	        // loop through event list and get
	        Deque<Event> rooms = new LinkedList<>();
	        for (Event event : events) {
	            if (event.start == true) {
	                rooms.offerLast(event);
	                result = Math.max(result, rooms.size());  // !!! not events, rooms
	            } else {
	                rooms.pollLast();  // does not matter which meeting get ended, the same
	            }
	        }
	        return result;
	    }
	    
	    private List<Event> getEventList(Interval[] intervals) {
	        List<Event> events = new ArrayList<>();
	        for (Interval interval : intervals) {
	            Event sEvt = new Event(interval.start, true);
	            Event eEvt = new Event(interval.end, false);
	            events.add(sEvt);
	            events.add(eEvt);
	        }
	        //Arrays.sort(events, someComparator<T>);
	        Collections.sort(events);
	        return events;
	    }
}
