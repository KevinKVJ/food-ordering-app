<template>
    <div class="wrapper">
        <n-card size="huge" :header-style="headerStyle">
            <template #header>
                <h3 :style="{ margin: '0' }">Merchant Infos</h3>
            </template>
            <template #header-extra>
                <!-- <n-button strong secondary round type="info">Edit</n-button> -->
            </template>
            <template #default>
                <n-divider />
                <n-descriptions label-placement="left" :column="1">
                    <n-descriptions-item label="Merchant Name">od.clientName</n-descriptions-item>
                    <n-descriptions-item label="Address">od.address  </n-descriptions-item>
                    <n-descriptions-item label="Phone"> od.phone</n-descriptions-item>
                    <n-descriptions-item label="Email">od.due </n-descriptions-item>
                    <n-descriptions-item label="Delivery Method"> od.deliveryMethod </n-descriptions-item>
                    <n-descriptions-item label="Comment"> od.comment</n-descriptions-item>
                </n-descriptions>
            </template>
        </n-card>
    </div>
</template>

<script setup lang="tsx">
const headerStyle = {
    paddingBottom: 0,
};
</script>

<style lang="scss" scoped></style>
