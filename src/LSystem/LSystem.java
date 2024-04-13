package LSystem;

        import java.util.HashMap;
        import java.util.Map;

public class LSystem {
    private String axiom;
    private Map<Character, String> productionRules;

    public LSystem(String axiom) {
        this.axiom = axiom;
        this.productionRules = new HashMap<>();
    }

    public void addProductionRule(char symbol, String replacement) {
        productionRules.put(symbol, replacement);
    }

    public String generate(int iterations) {
        String result = axiom;
        for (int i = 0; i < iterations; i++) {
            StringBuilder nextIteration = new StringBuilder();
            for (char c : result.toCharArray()) {
                String replacement = productionRules.getOrDefault(c, String.valueOf(c));
                nextIteration.append(replacement);
            }
            result = nextIteration.toString();
        }
        return result;
    }
}
