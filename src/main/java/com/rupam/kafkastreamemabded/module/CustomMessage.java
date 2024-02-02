package com.rupam.kafkastreamemabded.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomMessage {

    private String dream;
    private boolean slept;
}
