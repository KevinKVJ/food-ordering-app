<script setup lang="tsx">
import { DataTableColumns, DataTableCreateRowKey, NButton, PaginationProps, NSpace } from 'naive-ui';
import type { orderData } from '../OdDataTypes';
import { ref } from 'vue';

const { tableData } = defineProps<{
    tableData: any[];
    // checkedRows: any[],
}>();

const emits = defineEmits<{
    (e: 'displayOrderDetail', orderDetail: orderData): void;
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
    {
        title: '',
        key: 'operator',
        render(rowData) {
            /* <NSpace type='info' vertical={false}> </NSpace> onClick={() => emits('deleteDataRow', [rowData.id])}*/
            return (
                <NSpace vertical={false} size={8} justify={'space-around'}>
                    <NButton type='info' size='small' onClick={() => handleOrderDetailsDisplay(rowData)}>
                        <span>Details</span>
                    </NButton>
                </NSpace>
            );
            // <NButton type='error' size='small' onClick={() => emits('deleteDataRow', [rowData.id])}>
            //     <span style={{ fontSize: '13px',fontWeight:600 }}>Delete</span>
            // </NButton>
        },
        width: 96,
        fixed: 'right',
        align: 'center',
    },
];

const handleOrderDetailsDisplay = (rowData: orderData) => {
    emits('displayOrderDetail', rowData);
};
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
        <n-data-table :columns="columns" :row-key="(obj:orderData) => obj.id" :data="tableData" :scroll-x="800"  :style="{ height: `800px` }" flex-height/>
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
