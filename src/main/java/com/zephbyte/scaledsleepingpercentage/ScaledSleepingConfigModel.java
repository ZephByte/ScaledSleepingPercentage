package com.zephbyte.scaledsleepingpercentage;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "scaled-sleeping-percentage", wrapperName = "ScaledSleepingConfig")
public class ScaledSleepingConfigModel{
    public int sleepingPercentage = 50;
}