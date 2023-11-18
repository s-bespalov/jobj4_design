package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        var box = new Box(0, 10);
        var name = box.whatsThis();
        assertThat(name)
                .isAlphabetic()
                .isEqualTo("Sphere");
    }

    @Test
    void isUnknown() {
        var box = new Box(2, 10);
        var name = box.whatsThis();
        assertThat(name)
                .isNotBlank()
                .isEqualTo("Unknown object");
    }

    @Test
    void whenVertex4Edge9999NumberOfVertices4() {
        var box = new Box(4, 9999);
        var vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isNotNegative()
                .isBetween(3, 5)
                .isEqualTo(4);
    }

    @Test
    void whenVertex99999Edge1NumberOfVerticesMinus1() {
        var box = new Box(99999, 1);
        var vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isNegative()
                .isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    void whenVertex1Edge15IsExistFalse() {
        var box = new Box(1, 15);
        assertThat(box.isExist())
                .isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void whenVertex4Edge71IsExistTrue() {
        var box = new Box(4, 71);
        assertThat(box.isExist())
                .isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void whenVertex8Edge30Area5400() {
        var box = new Box(8, 30);
        var area = box.getArea();
        assertThat(area)
                .isPositive()
                .isBetween(5399d, 5401d)
                .isCloseTo(5400d, withPrecision(0.01d));
    }

    @Test
    void whenVertex0Edge10Area1256Dot63() {
        var box = new Box(0, 10);
        var area = box.getArea();
        assertThat(area)
                .isPositive()
                .isBetween(1255d, 1257d)
                .isCloseTo(1256.63d, withPrecision(0.01d));
    }
}