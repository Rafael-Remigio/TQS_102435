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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (hits ^ (hits >>> 32));
        result = prime * result + (int) (misses ^ (misses >>> 32));
        result = prime * result + (int) (acesses ^ (acesses >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Info other = (Info) obj;
        if (hits != other.hits)
            return false;
        if (misses != other.misses)
            return false;
        if (acesses != other.acesses)
            return false;
        return true;
    }

    
}
