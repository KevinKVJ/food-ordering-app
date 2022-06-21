<script setup lang="tsx">
import { DataTableColumns, DataTableCreateRowKey, NButton, PaginationProps } from 'naive-ui';
import type { orderData } from '../OdDataTypes';

const { tableData } = defineProps<{
    tableData: any[];
    // checkedRows: any[],
}>();

const columns: DataTableColumns<orderData> = [
    {
        title: 'No.',
        key: 'id',
        align: 'center',
    },
    {
        title: 'Client Name',
        key: 'clientId',
        align: 'center',
    },
    {
        title: 'Status',
        key: 'statusId',
    },
    {
        title: 'Address',
        key: 'address',
    },
    {
        title: 'Phone',
        key: 'phone',
    },
    {
        title: 'DeliveryMethod',
        key: 'deliveryMethodId',
    },
    {
        title: 'Total Price',
        key: 'totalPrice',
    },
    // {
    //     title:'Payment Method',
    //     key: "paymentMethod"
    // },
];
</script>
<template>
    <div class="wrapper">
        <!-- :columns="columns"
        :data="tableData"
            :row-key="(obj:productData) => obj.id"
            :checked-row-keys="checkedRows"
            @update:checked-row-keys="(rowKeys:RowKey[]) => emits('update:checkedRows',rowKeys)" -->
            <!-- :bordered="false" -->
            <!-- flex-height -->
            <!-- :style="{ height: '100%' }" -->
            <!-- :bordered="false" -->
            <!-- :single-line="false" -->
        <n-data-table
            :columns="columns"
            :row-key="(obj:orderData) => obj.id"
            :data="tableData"
            :scroll-x="800"
        />
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
