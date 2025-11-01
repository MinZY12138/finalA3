package game.api;

import game.mysteriostore.Price;
import java.util.List;
import java.util.Map;

/**
 * Defines the contract for any service that can provide prices for items.
 * Implementations can fetch prices from a remote API, local file, or random generator.
 */
public interface PriceService {

    /**
     * Returns the price for a single item SKU.
     * @param sku unique identifier (e.g., "AXE", "TORCH", "BOW")
     * @return the corresponding Price
     */
    Price getPrice(String sku);

    /**
     * Returns prices for multiple SKUs in one call.
     * @param skus list of item identifiers
     * @return map of SKU → Price
     */
    Map<String, Price> getPrices(List<String> skus);
}
