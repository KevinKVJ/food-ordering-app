<script setup lang="ts">
import {
    NSpace,
    NLayout,
    NLayoutHeader,
    NLayoutContent,
    NLayoutFooter,
    NGrid,
    NGi
} from "naive-ui";
</script>

<template>
    <div class="common-layout">
        <n-space vertical size="large">
            <n-layout>
                <n-layout-header>
                    颐和园路 
                    <div class="header"></div>
                </n-layout-header>
                <!-- <n-layout-content content-style="padding: 24px;"> -->
                <n-layout-content>
                    <n-grid x-gap="12" :cols="24">
                        <n-gi :span="3" :suffix="true">
                            <div class="light-green" >
                                fsdsfa
                            </div>
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <n-gi>
                            <div class="light-green" />
                        </n-gi>
                        <n-gi>
                            <div class="green" />
                        </n-gi>
                        <!-- <n-gi>
                            <div class="light-green" />
                        </n-gi> -->
                        <!-- <n-gi>
                            <div class="green" />
                        </n-gi> -->
                    </n-grid>
                </n-layout-content>
                <n-layout-footer>成府路</n-layout-footer>
            </n-layout>
        </n-space>
    </div>
</template>

<style lang="scss" scoped>
.n-layout-header,
.n-layout-footer {
    background: rgba(128, 128, 128, 0.2);
    /* padding: 24px; */
}

/* .n-layout-sider {
  background: rgba(128, 128, 128, 0.3);
} */

.n-layout-content {
    background: rgba(128, 128, 128, 0.4);
}

.light-green {
    height: 108px;
    background-color: rgba(0, 128, 0, 0.12);
}
.green {
    height: 108px;
    background-color: rgba(0, 128, 0, 0.24);
}
</style>
