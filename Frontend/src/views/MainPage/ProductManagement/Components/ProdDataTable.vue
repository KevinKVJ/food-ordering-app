<script setup lang="tsx">
import { ref } from 'vue';
import type { DataTableColumns, PaginationProps } from 'naive-ui';
import { Random } from 'mockjs';
import type { productData } from '../ProductDataTypes';

const props = defineProps({
    tableData: Array,
});


const pagination = { pageSize: 15 } as PaginationProps;

const columns: DataTableColumns = [
    {
        type: 'selection',
        // width: '15',
        fixed: 'left',
    },
    {
        title: 'Image',
        key: 'image',
        width: 80,
        fixed: 'left',
        render(rowObj, index) {
            return <n-avatar bordered size={48} src={Random.dataImage('400x400', `hello product ${index}`)} />;
        },
        align: 'center',
    },
    {
        title: 'ID',
        key: 'id',
        align: 'center',
        width: '65',
        fixed: 'left',
        // render(row){
        //     return <div>{row.id}</div>
        // }
    },
    {
        title: 'Name',
        key: 'name',
        width: '120',
        fixed: 'left',
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{`row ${index}`}</span>;
        // },
        // ellipsis: true,
    },
    {
        title: 'Price',
        key: 'price',
        // width: 80,
        // fixed: 'left',
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{row.price}</span>;
        // },
    },
    {
        title: 'Monthly sales',
        key: 'monthly',
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{`row ${index}`}</span>;
        // },
        // width: 80,
    },
    // {
    //     title: 'Options',
    //     key: 'options',
    //     width: 80,
    //     render(row: any, index: number) {
    //         return <span>{`row ${index}`}</span>;
    //     },
    // },
    {
        title: 'Inventory',
        key: 'inventory',
        // render(row: any, index: number) {
        //     return <span>{`row ${index}`}</span>;
        // },
        // width: 80,
        // fixed: 'right',
    },
    {
        title: 'Discount',
        key: 'discount',
        // width: 80
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{index}</span>;
        // },
        // fixed: 'right',
    },
];
</script>

<template>
    <div class="wrapper">
        <n-data-table
            :columns="columns"
            :data="props.tableData"
            :pagination="pagination"
            :style="{ height: '100%' }"
            flex-height
            :scroll-x="800"
            :row-key="(obj:productData) => obj.id"
            :single-line="false"
        />
    </div>
</template>

<style scoped lang="scss">
.wrapper {
    height: 100%;
}
</style>
