public class Results
{
    private int hits;
    private int misses;
    private double hitRatio;
    private double missRatio;
    private int[] history;
    
    public Results(int h, int m, int[] hist)
    {
        hits = h;
        misses = m;
        hitRatio = (double)h/(double)(h+m);
        missRatio = (double)m/(double)(h+m);
        history = hist;
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

    public int[] getHistory()
    {
        return this.history;
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

    public void updateHistory(int[] hist)
    {
        this.history = hist;
    }

    public void updateAll(int h, int m, int[] hist)
    {
        this.hits = h;
        this.misses = m;
        this.hitRatio = h/(h+m);
        this.missRatio = m/(h+m);
        this.history = hist;
    }
}