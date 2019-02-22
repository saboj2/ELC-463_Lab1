/*
 * Wraps the results into anan object
 */
public class Results
{
    private int hits;
    private int misses;
    private double hitRatio;
    private double missRatio;
    
    /*
     * Constructor for the results object
     */
    public Results(int h, int m)
    {
        hits = h;
        misses = m;
        hitRatio = (double)h/(double)(h+m);
        missRatio = (double)m/(double)(h+m);
    }

    /*
     * Return the hits
     */
    public int getHits()
    {
        return this.hits;
    }

    /*
     * Return the misses
     */
    public int getMisses()
    {
        return this.misses;
    }

    /*
     * Return the hit ratio
     */
    public double getHitRatio()
    {
        return this.hitRatio;
    }

    /*
     * Return the miss ratio
     */
    public double getMissRatio()
    {
        return this.missRatio;
    }
}