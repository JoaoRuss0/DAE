<template>
    <div>
        <b-container>
            <h1 class="mb-4 mt-4">
                New Course
            </h1>
            <form @submit.prevent="create">
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col><input v-model="name" type="text"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Code:</strong></b-col>
                    <b-col><input v-model="code" type="number"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link :to="{name: 'courses'}">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                    <b-col>
                        <button type="reset" class="btn btn-primary">RESET</button>
                        <button @click.prevent="create" class="btn btn-success">CREATE</button>
                    </b-col>
                </b-row>
            </form>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            name: null,
            code: null
        }
    },
    methods: {
        create () {
            this.$axios
                .$post('/api/courses', {
                    name: this.name,
                    code: this.code
                })
                .then(() => {
                    this.$router.push('/')
                })
        }
    }
}
</script>
