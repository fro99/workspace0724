package com.kh.example.gearrent;

import java.util.*;

public abstract class Device {
    private String id;
    private String name;
    private String category;
    private int borrowCount;
    private Set<String> tags;

    public Device(String id, String name, String category, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.borrowCount = 0;
        if (tags != null) {
            this.tags = new HashSet<>(tags);
        } else {
            this.tags = new HashSet<>();
        }
    }

    public abstract int getBorrowLimitDays();
    public abstract int calcLateFee(int overdueDays);

    public void increaseBorrowCount() {
        borrowCount++;
    }

    public boolean hasTag(String tag) {
        if (tag == null) return false;
        for (String t : tags) {
            if (t.equalsIgnoreCase(tag)) return true;
        }
        return false;
    }

    public String getId() { 
    	return id; 
    }
    public String getName() { 
    	return name; 
    }
    public String getCategory() { 
    	return category;
    }
    public int getBorrowCount() { 
    	return borrowCount; 
    }
    public Set<String> getTags() { 
    	return Collections.unmodifiableSet(tags); 
    }

    public void setName(String name) { 
    	this.name = name; 
    }
    public void setCategory(String category) { 
    	this.category = category; 
    }
    public void setTags(Set<String> tags) { 
    	this.tags = new HashSet<>(tags); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
    	 String typeTag = tags.stream()
                 .filter(tag -> tag.equalsIgnoreCase("미러리스") || tag.equalsIgnoreCase("노트북"))
                 .findFirst()
                 .orElse(category);
        return String.format(" - [%s] %s (%s) | 대여수=%d | tags=%s",
                id, name, typeTag, borrowCount, tags);
    }
}