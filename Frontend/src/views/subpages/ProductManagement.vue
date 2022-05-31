<script setup lang="tsx">
import { h, reactive } from 'vue';
import type { DataTableColumns, PaginationProps } from 'naive-ui';

const columns: DataTableColumns = [
    {
        type: 'selection',
        width: 10,
        fixed: 'left',
    },
    {
        title: 'Image',
        key: 'image',
        width: 30,
        fixed: 'left',
        render() {
            return <n-avatar bordered size={48} src='https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg' />;
        },
    },
    {
        title: 'Name',
        key: 'name1',
        width: 30,
        fixed: 'left',
        render(row: any, index: number) {
            // return h('span', ['row ', index]);
            return <span>{`row ${index}`}</span>;
        },
        ellipsis:true
    },
    {
        title: 'Price',
        key: 'price',
        width: 50,
        // fixed: 'left',
        render(row: any, index: number) {
            // return h('span', ['row ', index]);
            return <span>{`row ${index}`}</span>;
        },
    },
    {
        title: 'Monthly sales',
        key: 'monthlySales',
        render(row: any, index: number) {
            // return h('span', ['row ', index]);
            return <span>{`row ${index}`}</span>;
        },
        width: 50,
    },
    {
        title: 'Options',
        key: 'options',
        width: 80,
        render(row: any, index: number) {
            return <span>{`row ${index}`}</span>;
        },
    },
    {
        title: 'Inventory',
        key: 'inventory',
        render(row: any, index: number) {
            return <span>{`row ${index}`}</span>;
        },
        width: 100,
        // fixed: 'right',
    },
    {
        title: 'Discount',
        key: 'discount',
        width: 100,
        render(row: any, index: number) {
            // return h('span', ['row ', index]);
            return <span>{index}</span>;
        },
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

// const pagination:PaginationProps = { pageSize: 20 };
</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <n-layout embedded class="contents-style">
                <!-- <n-layout-header>颐和园路</n-layout-header>
                <n-layout-content content-style="padding: 24px;"> 平山道 </n-layout-content>
                <n-layout-footer>成府路</n-layout-footer> -->
                <n-data-table :columns="columns" :data="data" :pagination="{ pageSize: 20 }" :max-height="250" :scroll-x="1800" />
            </n-layout>
            <n-layout-sider content-style="padding: 24px;" show-trigger="bar">
                <categories-list />
            </n-layout-sider>
        </n-layout>
    </div>
</template>

<style lang="scss" scoped>
.contents-style {
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
