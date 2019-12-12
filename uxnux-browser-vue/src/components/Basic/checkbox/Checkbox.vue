<template>
  <label class="ux-checkbox">
    <span class="ux-check">
      <input
        type="checkbox"
        :class="inputClasses"
        :value="label"
        v-model="model"
        :name="name"
        @change="change"
        @focus="onFocus"
        @blur="onBlur">
    </span>
    <slot><span v-if="showSlot">{{ label }}</span></slot>
  </label>
</template>
