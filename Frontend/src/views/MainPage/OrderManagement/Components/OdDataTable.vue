<script setup lang="tsx">
import { DataTableColumns, DataTableCreateRowKey, NButton, PaginationProps } from 'naive-ui';
import type { orderData } from '../OdDataTypes';

const columns: DataTableColumns<orderData> = [
    {
        title: 'No.',
        key:"id",
        align:'center'
    },
    {
        title:'Client Name',
        key: "clientName"
    },
    {
        title:'Address',
        key: "address"
    },
    // {
    //     title:'Phone',
    //     key: "phone"
    // },
    // {
    //     title:'Payment Method',
    //     key: "paymentMethod"
    // },
    {
        title:'Payment Method',
        key: "paymentMethod"
    },
    {
        title:'Payment Method',
        key: "paymentMethod"
    },
    {
        title:'Payment Method',
        key: "paymentMethod"
    },
];
</script>
<template>
    <div class="wrapper">
        <!-- :columns="columns"
        :data="tableData"
            :row-key="(obj:productData) => obj.id"
            :checked-row-keys="checkedRows"
            @update:checked-row-keys="(rowKeys:RowKey[]) => emits('update:checkedRows',rowKeys)" -->
        <n-data-table :columns="columns" :style="{ height: '100%' }" flex-height :scroll-x="800" :single-line="false" />
    </div>
</template>

<style lang="scss" scoped>
.wrapper {
    height: 100%;
}
.col-operation {
    padding: 0px;
}
</style>
