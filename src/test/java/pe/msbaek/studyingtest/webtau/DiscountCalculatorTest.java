package pe.msbaek.studyingtest.webtau;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.testingisdocumenting.webtau.data.table.TableData;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testingisdocumenting.webtau.WebTauCore.*;

public class DiscountCalculatorTest {
    @Test
    void discountBasedUniqueGenresAbove15DollarsLimitedBy3() {
        List<Game> games = createGames();

        int discount = getDiscount(games);

        assertThat(discount).isEqualTo(30);
    }

    @Test
    void assert_with_table() {
        TableData expected = table("id", "priceUsd", "type",
                _____________________,
                1, 12, "RPG",
                2, 28, "RPG");

        List<Game> games = createGames();

        TableData value = propertiesTable(games);
        trace("all games", value);

//        actual(games, "top2").should(equal(expected));
        actual(games).should(equal(value));
    }

    @NotNull
    private List<Game> createGames() {
        TableData table = table("id", "type", "price",
                _____________________,
                1L, "RPG", 12,
                cell.above.plus(1), "RPG", 28,
                cell.above.plus(1), "RPG", 70,
                cell.above.plus(1), "FPS", 30,
                cell.above.plus(1), "FPS", 10,
                cell.above.plus(1), "RTS", 60,
                cell.above.plus(1), "Sport", 30);
        return createGames(table);
    }

    private List<Game> createGames(TableData table) {
        return table.rowsStream()
                .map(this::createGame)
                .toList();
    }

    @NotNull
    private Game createGame(org.testingisdocumenting.webtau.data.table.Record row) {
        Game game = new Game();
        game.setId(row.get("id"));
        game.setType(row.get("type"));
        game.setPriceUsd(BigDecimal.valueOf((int) row.get("price", 0)));
        return game;
    }

    private int getDiscount(List<Game> games) {
        long numberOfDisctinctTypes = games.stream()
                .filter(game -> game.getPriceUsd().compareTo(BigDecimal.valueOf(15)) > 0)
                .map(Game::getType)
                .distinct()
                .count();
        long limitedDistinctTypes = Math.min(3, numberOfDisctinctTypes);
        int discount = (int) (limitedDistinctTypes * 10);
        return discount;
    }
}