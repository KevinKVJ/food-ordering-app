<script setup lang="tsx">
// import { ref, toRaw, unref, VNodeChild } from 'vue';
import { DataTableColumns, DataTableCreateRowKey, NButton, PaginationProps } from 'naive-ui';
import { Random } from 'mockjs';
import type { productData } from '../ProductDataTypes';
import type { RowKey } from 'naive-ui/es/data-table/src/interface';

const { tableData } = defineProps({
    tableData: Array,
    checkedRows: Array,
    // changeCheckedRow: Function
});

const emits = defineEmits(['deleteDataRow','update:checkedRows']);

// const pagination = { pageSize: 15 } as PaginationProps;

const columns: DataTableColumns<productData> = [
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
        render(rowData, index) {
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
    {
        key: 'operation',
        render(rowData) {
            return (
                <NButton type='error' size='small' onClick={() => emits('deleteDataRow', [rowData.id])}>
                    Delete
                </NButton>
            );
        },
        width: 80,
        fixed: 'right',
        align: 'center',
        className: 'col-operation',
        // cellProps:() => {
        //     return {
        //         innerHTML: 'div'
        //     }
        // }
    },
];
</script>

<template>
<!-- emits('update:checkedRows',rowKeys)   changeCheckedRow?.(rowKeys)-->
        <!-- :checked-row-keys="checkedRows" -->
        <!-- :pagination="pagination" -->
    <div class="wrapper">
        <n-data-table
            :columns="columns"
            :data="tableData"
            :style="{ height: '100%' }"
            flex-height
            :scroll-x="800"
            :row-key="(obj:productData) => obj.id"
            :single-line="false"
            :checked-row-keys="checkedRows"
            @update:checked-row-keys="(rowKeys:RowKey[]) => emits('update:checkedRows',rowKeys)"
        />
    </div>
</template>

<style scoped lang="scss">
.wrapper {
    height: 100%;
}
.col-operation {
    padding: 0px;
}
</style>
