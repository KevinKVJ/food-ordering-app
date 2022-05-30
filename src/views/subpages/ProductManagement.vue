<script setup lang="ts">
import { h, reactive } from 'vue';
import { DataTableColumns } from 'naive-ui';

const columns:DataTableColumns = [
    {
        type: 'selection',
        // fixed: 'left',
    },
    {
        title: 'Name',
        key: 'name',
        width: 200,
        // fixed: 'left',
    },
    {
        title: 'Age',
        key: 'age',
        width: 100,
        // fixed: 'left',
    },
    {
        title: 'Row',
        key: 'row',
        render(row: any, index: number) {
            return h('span', ['row ', index]);
        },
    },
    {
        title: 'Row1',
        key: 'row1',
        render(row: any, index: number) {
            return h('span', ['row ', index]);
        },
    },
    {
        title: 'Row2',
        key: 'row2',
        render(row: any, index: number) {
            return h('span', ['row ', index]);
        },
        width: 100,
        // fixed: 'right',
    },
    {
        title: 'Address',
        key: 'address',
        width: 200,
        // fixed: 'right',
    },
];

const data = reactive(
    Array.apply(null, Array(46)).map((_, index) => ({
        key: index,
        name: `Edward King ${index}`,
        age: 32,
        address: `London, Park Lane no. ${index}`,
    }))
);

const pagination = { pageSize: 20 };
</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <n-layout embedded class="contents-style">
                <!-- <n-layout-header>颐和园路</n-layout-header>
                <n-layout-content content-style="padding: 24px;"> 平山道 </n-layout-content>
                <n-layout-footer>成府路</n-layout-footer> -->
                <n-data-table :columns="columns" :data="data" :pagination="pagination" :max-height="250" :scroll-x="1800" />
            </n-layout>
            <n-layout-sider content-style="padding: 24px;" show-trigger="bar"> 海淀桥 </n-layout-sider>
            
        </n-layout>
    </div>
</template>

<style lang="scss" scoped>
.contents-style{
    padding: 30px;
}

.n-layout-header,
.n-layout-footer {
    background: rgba(128, 128, 128, 0.2);
    padding: 24px;
}

.n-layout-sider {
    background: rgba(128, 128, 128, 0.3);
}

.n-layout-content {
    background: rgba(128, 128, 128, 0.4);
}
</style>
