<template>
    <div>
        <b-container>
            <b-table striped over :items="subjects" :fields="fields">
                <template #cell(buttons)="data">
                    <a class="btn btn-primary" :href="'subjects/' + data.item.code">View</a>
                    <a class="btn btn-success" :href="'subjects/' + data.item.code + '/update'">Update</a>
                    <a class="btn btn-danger" @click.prevent="deleteSubject(data.item.code)">Delete</a>
                </template>
            </b-table>
            <nuxt-link :to="{name: 'subjects-create'}">
                <button class="btn btn-dark">Create a New Subject</button>
            </nuxt-link>
            <nuxt-link to="/">
                <button class="btn btn-warning">Return</button>
            </nuxt-link>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            fields: ['code', 'name', 'courseCode', 'courseYear', 'scholarYear', 'buttons'],
            subjects: []
        }
    },
    created () {
        this.$axios.$get('/api/subjects').then((subjects) => {
            this.subjects = subjects
        })
    },
    methods: {
        deleteSubject: async function (subjectCode) {
            await this.$axios.$delete('api/subjects/' + subjectCode)

            this.$axios.$get('/api/subjects').then((subjects) => {
                this.subjects = []
                this.subjects.push(...subjects)
            })
        }
    }
}
</script>
