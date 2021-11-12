<template>
	<form @submit.prevent="upload">
		<!-- Styled -->
		<b-form-file
			v-model="file"
			:state="hasFile"
			placeholder="Choose a file or drop it here..."
			drop-placeholder="Drop file here..."
		></b-form-file>
		<div class="mt-3">Selected file: {{ file ? file.name : "" }}</div>
		<nuxt-link class="btn btn-link" :to="`/students/${this.username}`"
			>Back</nuxt-link
		>
		<b-button type="submit" :disabled="!hasFile">Upload</b-button>
	</form>
</template>
<script>
export default {
	auth: false,
	data() {
		return {
			username: this.$auth.user.sub,
			file: null,
		};
	},
	computed: {
		hasFile() {
			return this.file != null;
		},
		formData() {
			let formData = new FormData();
			formData.append("username", this.$auth.user.sub);
			if (this.file) {
				formData.append("file", this.file);
			}
			return formData;
		},
	},
	methods: {
		upload() {
			if (!this.hasFile) {
				return;
			}
			let promisse = this.$axios.$post(
				"/api/documents/upload",
				this.formData,
				{
					headers: {
						"Content-Type": "multipart/form-data",
					},
				}
			);
			promisse.then(() => {
				this.$toast.success("File uploaded!").goAway(3000);
			});
			promisse.catch(() => {
				this.$toast.error("Sorry, could no upload file!").goAway(3000);
			});
		},
	},
};
</script>
