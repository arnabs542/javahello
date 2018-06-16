package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MeetingRoomsII {
	
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
