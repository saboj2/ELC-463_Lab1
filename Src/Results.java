public class Results
{
    public int hits;
    public int misses;
    public double hitRatio;
    public double missRatio;
    public results(int h, int m)
    {
        hits = h;
        misses = m;
        hitRatio = h/(h+m);
        missRatio = m/(h+m);
    }

    public int getHits()
    {
        return hits;
    }

    public int getMisses()
    {
        return misses;
    }

    public double getHitRatio()
    {
        return hitRatio;
    }

    public double getMissRatio()
    {
        return missRatio;
    }

    public void updateHits(int h)
    {
        hits = h;
    }

    public void updateMisses(int m)
    {
        misses = m;
    }

    public void updateHitRatio()
    {
        hitRatio = hits/(hit + misses);
    }

    public void updateHitRatio()
    {
        missRatio = misses/(hit + misses);
    }

    public void updateAll(int h, int m)
    {
        hits = h;
        misses = m;
        hitRatio = h/(h+m);
        missRatio = m/(h+m);
    }
}