
package lv.bootcamp.shelter.task4;

import lv.bootcamp.shelter.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Task 4: Collection and sorting tests
 *
 * Practice:
 * - AssertJ list assertions (extracting, containsExactly)
 * - Testing sort order
 * - Testing null/empty input handling
 */
@DisplayName("AnimalSorter")
class AnimalSorterTest {

    private AnimalSorter sorter;

    private Animal buddy;
    private Animal luna;
    private Animal max;
    private Animal bella;

    @BeforeEach
    void setUp() {
        sorter = new AnimalSorter();
        buddy = new Animal("Buddy", "Dog", 3, true, LocalDate.of(2026, 1, 15));
        luna = new Animal("Luna", "Cat", 2, true, LocalDate.of(2026, 1, 10));
        max = new Animal("Max", "Dog", 5, false, LocalDate.of(2026, 1, 20));
        bella = new Animal("Bella", "Cat", 1, true, LocalDate.of(2026, 1, 5));
    }

    // --- sortByAge ---

    @Test
    @DisplayName("sortByAge: returns animals ordered youngest to oldest")
    void shouldSortByAgeAscending() {
        List<Animal> animals = List.of(buddy, luna, max, bella);

        List<Animal> result = sorter.sortByAge(animals);

        assertThat(result)
                .extracting(Animal::getName)
                .containsExactly("Bella", "Luna", "Buddy", "Max");
    }

    @Test
    @DisplayName("sortByAge: returns empty list for null input")
    void shouldReturnEmptyForNullInput() {
        List<Animal> result = sorter.sortByAge(null);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("sortByAge: returns empty list for empty input")
    void shouldReturnEmptyForEmptyInput() {
        List<Animal> result = sorter.sortByAge(List.of());

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("sortByAge: does not modify the original list")
    void shouldNotModifyOriginalList() {
        List<Animal> animals = new ArrayList<>(
                List.of(buddy, luna, max, bella)
        );

        List<Animal> result = sorter.sortByAge(animals);

        assertThat(animals)
                .extracting(Animal::getName)
                .containsExactly("Buddy", "Luna", "Max", "Bella");

        assertThat(result)
                .extracting(Animal::getName)
                .containsExactly("Bella", "Luna", "Buddy", "Max");
    }

    // --- sortByName ---

    @Test
    @DisplayName("sortByName: returns animals in alphabetical order")
    void shouldSortByNameAlphabetically() {
        List<Animal> animals = List.of(buddy, luna, max, bella);

        List<Animal> result = sorter.sortByName(animals);

        assertThat(result)
                .extracting(Animal::getName)
                .containsExactly("Bella", "Buddy", "Luna", "Max");
    }

    @Test
    @DisplayName("sortByName: is case-insensitive")
    void shouldSortNamesCaseInsensitively() {
        Animal zebra = new Animal("zebra", "Dog", 3, true, LocalDate.now());
        Animal alpha = new Animal("Alpha", "Cat", 2, true, LocalDate.now());

        List<Animal> animals = List.of(zebra, alpha);

        List<Animal> result = sorter.sortByName(animals);

        assertThat(result)
                .extracting(Animal::getName)
                .containsExactly("Alpha", "zebra");
    }

    // --- sortByIntakeDate ---

    @Test
    @DisplayName("sortByIntakeDate: returns animals from earliest to latest")
    void shouldSortByIntakeDateAscending() {
        List<Animal> animals = List.of(buddy, luna, max, bella);

        List<Animal> result = sorter.sortByIntakeDate(animals);

        assertThat(result)
                .extracting(Animal::getName)
                .containsExactly("Bella", "Luna", "Buddy", "Max");
    }

    // --- sortBySpeciesThenAgeDescending ---

    @Test
    @DisplayName("sortBySpeciesThenAgeDescending: groups by species then orders by age desc")
    void shouldSortBySpeciesThenAgeDesc() {
        List<Animal> animals = List.of(buddy, luna, max, bella);

        List<Animal> result = sorter.sortBySpeciesThenAgeDescending(animals);

        assertThat(result)
                .extracting(Animal::getName)
                .containsExactly("Luna", "Bella", "Max", "Buddy");
    }
}
