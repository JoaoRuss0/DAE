<template>
    <div>
        <b-container>
            <b-table striped over :items="courses" :fields="fields">
                <template #cell(buttons)="data">
                    <a class="btn btn-primary" :href="'courses/' + data.item.code">View</a>
                    <a class="btn btn-success" :href="'courses/' + data.item.code + '/update'">Update</a>
                    <a class="btn btn-danger" @click.prevent="deleteCourse(data.item.code)">Delete</a>
                </template>
            </b-table>
            <nuxt-link :to="{name: 'courses-create'}">
                <button class="btn btn-dark">Create a New Course</button>
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
            fields: ['code', 'name', 'buttons'],
            courses: []
        }
    },
    created () {
        this.$axios.$get('/api/courses').then((courses) => {
            this.courses = courses
        })
    },
    methods: {
        deleteCourse: async function (courseCode) {
            await this.$axios.$delete('api/courses/' + courseCode)

            this.$axios.$get('/api/courses').then((courses) => {
                this.courses = []
                this.courses.push(...courses)
            })
        }
    }
}
</script>
