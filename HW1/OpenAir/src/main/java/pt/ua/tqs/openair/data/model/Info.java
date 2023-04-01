package pt.ua.tqs.openair.data.model;

public class Info {
    long hits;
    long misses;
    long acesses;

    

    public Info(long hits, long misses, long acesses) {
        this.hits = hits;
        this.misses = misses;
        this.acesses = acesses;
    }
    
    public long getHits() {
        return hits;
    }
    public void setHits(long hits) {
        this.hits = hits;
    }
    public long getMisses() {
        return misses;
    }
    public void setMisses(long misses) {
        this.misses = misses;
    }
    public long getAcesses() {
        return acesses;
    }
    public void setAcesses(long acesses) {
        this.acesses = acesses;
    }

    
}
