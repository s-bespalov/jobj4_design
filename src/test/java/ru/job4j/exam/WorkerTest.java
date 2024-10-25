package ru.job4j.exam;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WorkerTest {

    @Test
    public void whenWorkerHasManagersThenReturnManagers() {
        Worker manager1 = new Worker(1, 4, "Manager1");
        Worker manager2 = new Worker(2, 0, "Manager2");
        Worker worker = new Worker(3, 1, "Worker1");
        Worker manager3 = new Worker(4, 0, "Manager4");

        List<Worker> workers = List.of(manager1, manager2, manager3, worker);
        List<Worker> managers = Worker.getAllManagers(workers, worker.getId());

        assertThat(managers)
                .hasSize(2)
                .contains(manager1, manager3)
                .doesNotContain(manager2, worker);
    }

    @Test
    public void whenManagerHasWorkersThenReturnWorkers() {
        Worker manager1 = new Worker(1, 4, "Manager1");
        Worker manager2 = new Worker(2, 0, "Manager2");
        Worker worker = new Worker(3, 1, "Worker1");
        Worker manager3 = new Worker(4, 0, "Manager3");
        Worker worker2 = new Worker(5, 1, "Worker2");

        List<Worker> workers = List.of(manager1, manager2, manager3, worker, worker2);
        List<Worker> managers = Worker.getAllSubWorkers(workers, 4);

        assertThat(managers)
                .hasSize(3)
                .contains(manager1, worker, worker2)
                .doesNotContain(manager2, manager3);
    }

    @Test
    public void whenManagerHasCycledHierarchyOfWorkersThenReturnWorkers() {
        Worker manager1 = new Worker(1, 0, "Manager1");
        Worker manager2 = new Worker(2, 3, "Manager2");
        Worker worker = new Worker(3, 5, "Worker1");
        Worker manager3 = new Worker(4, 0, "Manager3");
        Worker worker2 = new Worker(5, 4, "Worker2");

        List<Worker> workers = List.of(manager1, manager2, manager3, worker, worker2);
        List<Worker> managers = Worker.getAllSubWorkers(workers, 4);

        assertThat(managers)
                .hasSize(4)
                .contains(manager2, worker, worker2, manager1)
                .doesNotContain(manager3);
    }

    @Test
    public void whenWorkerHasNoManagersThenReturnEmptyList() {
        Worker worker = new Worker(3, 1, "Worker1");

        List<Worker> workers = List.of(worker);
        List<Worker> managers = Worker.getAllManagers(workers, 3);

        assertThat(managers).isEmpty();
    }

    @Test
    public void whenManagerHasNoSubworkersThenReturnEmptyList() {
        Worker manager = new Worker(1, 0, "Manager1");

        List<Worker> workers = List.of(manager);
        List<Worker> subWorkers = Worker.getAllSubWorkers(workers, manager.getId());

        assertThat(subWorkers).isEmpty();
    }
}