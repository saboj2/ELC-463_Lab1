public class Results
{
    public int hits;
    public int misses;
    public double hitRatio;
    public double missRatio;
    
    public Results(int h, int m)
    {
        hits = h;
        misses = m;
        hitRatio = h/(h+m);
        missRatio = m/(h+m);
    }

    public int getHits()
    {
        return this.hits;
    }

    public int getMisses()
    {
        return this.misses;
    }

    public double getHitRatio()
    {
        return this.hitRatio;
    }

    public double getMissRatio()
    {
        return this.missRatio;
    }

    public void updateHits(int h)
    {
        this.hits = h;
    }

    public void updateMisses(int m)
    {
        this.misses = m;
    }

    public void updateHitRatio()
    {
        this.hitRatio = this.hits/(this.hits + this.misses);
    }

    public void updateMissRatio()
    {
        this.missRatio = this.misses/(this.hits + this.misses);
    }

    public void updateAll(int h, int m)
    {
        this.hits = h;
        this.misses = m;
        this.hitRatio = h/(h+m);
        this.missRatio = m/(h+m);
    }
}