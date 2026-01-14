package project.Map;

public class PerlinNoise {

    private final int[] permutation = new int[512];

    public PerlinNoise(long seed) {
        int[] p = new int[256];

        // Fill with 0..255
        for (int i = 0; i < 256; i++) {
            p[i] = i;
        }

        // Shuffle using seed
        java.util.Random rand = new java.util.Random(seed);
        for (int i = 255; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = p[i];
            p[i] = p[index];
            p[index] = temp;
        }

        // Duplicate into permutation[]
        for (int i = 0; i < 512; i++) {
            permutation[i] = p[i & 255];
        }
    }

    // Fade function: 6t^5 - 15t^4 + 10t^3
    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    // Linear interpolation
    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    // Gradient function
    private double grad(int hash, double x, double y) {
        int h = hash & 3; // Only need 2 bits for 2D gradients
        double u = (h < 2) ? x : y;
        double v = (h < 2) ? y : x;
        return ((h & 1) == 0 ? u : -u) +
                ((h & 2) == 0 ? v : -v);
    }

    // 2D Perlin noise
    public double noise(double x, double y) {
        // Find unit grid cell containing point
        int X = (int)Math.floor(x) & 255;
        int Y = (int)Math.floor(y) & 255;

        // Relative x, y inside cell
        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);

        // Fade curves
        double u = fade(xf);
        double v = fade(yf);

        // Hash coordinates of the 4 corners
        int aa = permutation[permutation[X] + Y];
        int ab = permutation[permutation[X] + Y + 1];
        int ba = permutation[permutation[X + 1] + Y];
        int bb = permutation[permutation[X + 1] + Y + 1];

        // Add blended results from 4 corners
        double x1 = lerp(u,
                grad(aa, xf, yf),
                grad(ba, xf - 1, yf));

        double x2 = lerp(u,
                grad(ab, xf, yf - 1),
                grad(bb, xf - 1, yf - 1));

        return lerp(v, x1, x2);
    }

    // Octave Perlin noise (fractal)
    public double octaveNoise(double x, double y, int octaves, double persistence) {
        double total = 0;
        double frequency = 1;
        double amplitude = 1;
        double maxValue = 0;

        for (int i = 0; i < octaves; i++) {
            total += noise(x * frequency, y * frequency) * amplitude;
            maxValue += amplitude;

            amplitude *= persistence;
            frequency *= 2;
        }

        return total / maxValue; // Normalize to [-1, 1]
    }

    public double ridgedNoise(double x, double y, int octaves, double gain, double lacunarity) {
        double sum = 0;
        double frequency = 1.0;
        double amplitude = 0.5;
        double prev = 1.0;

        for (int i = 0; i < octaves; i++) {
            double n = noise(x * frequency, y * frequency);
            n = 1.0 - Math.abs(n);   // invert valleys → peaks
            n *= n;                  // sharpen ridges

            sum += n * amplitude * prev;

            prev = n;
            frequency *= lacunarity;
            amplitude *= gain;
        }

        return sum;
    }


}
