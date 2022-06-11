<script setup lang="tsx">
import { reactive } from 'vue';
import { productCategory } from '../ProductDataTypes';

const data = reactive([
    {
        name: 'Junk Food',
        items: ['1', '22'],
    },
    {
        name: 'Junk Food1',
        items: ['333', '4444'],
    },
]);

const props = defineProps<{
    categoryData?: productCategory[];
}>();

const emits = defineEmits(['getProdsByCategory', 'getAllProds']);
</script>
<template>
    <n-list class="list-styles">
        <!-- <template #header>
      hhh
    </template> -->
        <template #footer>
            <div class="list-item-style2">
                <n-button>Edit</n-button>
            </div>
        </template>
        <n-list-item>
            <div class="list-item-style1" @click="emits('getAllProds')">
                <!-- (${item.items.length})` -->
                All Products
            </div>
        </n-list-item>
        <template v-for="(item, index) in props.categoryData">
            <n-list-item>
                <!-- <template #prefix>
        <n-button>Pr</n-button>
      </template> -->
                <!-- <template #suffix>
        <n-button>Su</n-button>
      </template> -->
                <!-- <n-thing title="Thing" title-extra="extra" description="description">
                    Biu<br />
                    Biu<br />
                    Biu<br />
                </n-thing> -->

                <div class="list-item-style1" @click="emits('getProdsByCategory', item.id)">
                    <!-- (${item.items.length})` -->
                    {{ `${item.name}` }}
                </div>
            </n-list-item>
        </template>
    </n-list>
</template>

<style lang="scss" scoped>
.list-styles {
    background-color: transparent;
}
.list-item-style1 {
    width: 100%;
    padding-left: 8px;
    cursor: pointer;
    user-select: none;
}
.list-item-style2 {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
