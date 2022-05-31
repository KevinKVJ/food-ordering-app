<script setup lang="tsx">
import { reactive } from 'vue';

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
        <template v-for="(item, index) in data">
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
                <div class="list-item-style1">
                    {{`${item.name} (${item.items.length})`}}
                </div>
            </n-list-item>
        </template>
    </n-list>
</template>

<style lang="scss" scoped>
.list-styles {
    background-color: transparent;
}
.list-item-style1{
    width: fit-content;
    padding-left: 8px;
}
.list-item-style2{
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
