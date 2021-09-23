<template>
	<b-container>
		<b-table striped over :items="students" :fields="fields" />
	</b-container>
</template>

<script>
export default {
	data() {
		return {
			fields: ["username", "name", "email", "password"],
			students: [],
		};
	},
	created() {
		this.$axios.$get("/api/students").then((students) => {
			this.students = students;
		});
	},
};
</script>

<style></style>