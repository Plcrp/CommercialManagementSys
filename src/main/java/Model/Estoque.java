package Model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> estoque;

    public Estoque() {
        this.estoque = new HashMap<>();
    }
}