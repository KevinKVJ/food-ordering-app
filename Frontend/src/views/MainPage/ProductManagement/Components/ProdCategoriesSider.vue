<script setup lang="tsx">
import { reactive, ref, computed, watchEffect, Ref, onMounted, watch, toRefs } from 'vue';
import { productCategory } from '../ProductDataTypes';

/* const data = reactive([
    {
        name: 'Junk Food',
        items: ['1', '22'],
    },
    {
        name: 'Junk Food1',
        items: ['333', '4444'],
    },
]); */

const props = defineProps<{
    categoryData: productCategory[];
}>();
const { categoryData } = toRefs(props);

const emits = defineEmits(['getProdsByCategory', 'getAllProds', 'update:categoryData']);

const showCategoriesEditing = ref<boolean>(false);
const showCategoriesEditingLoading = ref<boolean>(false);

// const categories = computed({
//     get: () => {
//         console.log(categoryData.value);
//         return categoryData.value.map(item => item.name);
//     },
//     set: newVal => {
//         console.log(newVal);

//         // emits('update:categoryData',newVal);
//     },
// });

const categories = ref<string[]>([]);
watchEffect(() => {
    categories.value = categoryData.value.map(item => item.name);
});

// ref<productCategory[]>(categoryData)   computed(() => categoryData.map((item) => item.name));
const handleAddCategory = (newCates: string[]) => {
    categories.value = newCates;
};

const cateRenderTag = (tag: string, index: number) => (
    <n-tag
        type='success'
        closable={true}
        // disabled={index > 3}
        onClose={() => {
            categories.value.splice(index, 1);
        }}>
        {tag}
    </n-tag>
);
</script>
<template>
    <div>
        <n-list class="list-styles">
            <template #header>
                <h3>Categories</h3>
            </template>
            <template #footer>
                <div class="list-item-style2">
                    <n-button @click="showCategoriesEditing = !showCategoriesEditing">Edit Categories</n-button>
                </div>
            </template>
            <n-list-item>
                <div class="list-item-style1" @click="emits('getAllProds')">All Products</div>
            </n-list-item>
            <template v-for="(item, index) in categoryData">
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
        <n-modal v-model:show="showCategoriesEditing" :mask-closable="false">
            <n-spin :show="showCategoriesEditingLoading">
                <template #description> Adding... </template>
                <n-card
                    closable
                    @close="showCategoriesEditing = false"
                    style="width: 600px"
                    :bordered="false"
                    size="huge"
                    role="dialog"
                    aria-modal="true"
                >
                    <template #header>Add(Edit) New Categories</template>
                    <!--  model="formAdd" -->
                    <n-form size="medium" :label-width="80">
                        <n-form-item path="tags" :show-label="false">
                            <n-dynamic-tags :value="categories" @update:value="handleAddCategory" :render-tag="cateRenderTag" />
                        </n-form-item>
                    </n-form>

                    <!-- <template #footer> 尾部 </template> -->
                </n-card>
            </n-spin>
        </n-modal>
    </div>
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
