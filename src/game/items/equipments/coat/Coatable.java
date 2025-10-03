package game.items.equipments.coat;

import java.util.Optional;

public interface Coatable {


    Optional<Coating> getCoating();

    void setCoating(Coating coating);

    void clearCoating();

    String coatedName();
}
