package org.api.stockmarket.engine.scheduling;

import org.api.stockmarket.core.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class MarketSchedulingServiceTest {
    @Mock
    private MarketActivityScheduler marketActivityScheduler;

    private MarketSchedulingService service;

    @BeforeEach
    void setUp() {
        service = new MarketSchedulingService(marketActivityScheduler);
    }

    @Test
    void updateInterval_shouldThrow_whenBelowMinimum() {
        BadRequestException ex = assertThrows(
                BadRequestException.class, () -> service.updateInterval(99)
        );

        assertEquals("Intervals must be 1 second or more", ex.getMessage());
        verifyNoInteractions(marketActivityScheduler);
    }

    @Test
    void updateInterval_shouldUpdate_whenValid() {
        service.updateInterval(1_000);

        verify(marketActivityScheduler).updateInterval(1_000);
    }

    @Test
    void pause_shouldStopScheduler() {
        service.pause();

        verify(marketActivityScheduler).stop();
    }

    @Test
    void resume_shouldScheduleScheduler() {
        service.resume();

        verify(marketActivityScheduler).schedule();
    }
}