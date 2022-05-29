<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://vuejs.org/api/sfc-script-setup.html#script-setup
// import HelloWorld from "./components/HelloWorld.vue";
import MainContainer from "@/components/Layout/NLayoutContainer.vue";
import { RouterView } from "vue-router";
</script>

    
<template>
    <main-container/>
    <router-view></router-view>
</template>

<style lang="scss">
html,body{
    height: 100%;
}
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    height: 100%;
}
/* .el-row {
    margin-bottom: 20px;
}
.el-row:last-child {
    margin-bottom: 0;
}
.el-col {
    border-radius: 4px;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

.bg-purple {
    background: #c084fc;
}

.bg-purple-light {
    background: #e5e9f2;
} */
</style>


<!-- <div class="common-layout">
        <n-space vertical size="large">
            <n-layout>
                <n-layout-header>颐和园路</n-layout-header>
                <n-layout-content content-style="padding: 24px;">
                    平山道
                </n-layout-content>
                <n-layout-footer>成府路</n-layout-footer>
            </n-layout>
        </n-space>
        <el-container>
            <el-header>Header</el-header>
            <el-main>
                <el-row>
                    <el-col :span="3">
                        <div class="grid-content bg-purple" />
                    </el-col>
                    <el-col :span="21">
                        <div class="grid-content bg-purple-light" />
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div> -->
