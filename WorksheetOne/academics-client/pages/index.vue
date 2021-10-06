<template>
    <div>
        <b-container>
            <b-table striped over :items="students" :fields="fieldsStudents"/>
            <nuxt-link :to="{name: 'students-create'}">
                <button class="btn btn-dark">Create a New Student</button>
            </nuxt-link>

            <br>
            <br>

            <b-table striped over :items="courses" :fields="fieldsCourses">
                <template #cell(buttons)="data">
                    <!--
                    <button type="button" class="btn btn-success" @click="viewCourse(data.item.code)">View</button>
                    <button type="button" class="btn btn-danger" @click="deleteCourse(data.item.code)">Delete</button>
                    -->
                    <a class="btn btn-primary" :href="'courses/' + data.item.code">View</a>
                    <a class="btn btn-success" :href="'courses/' + data.item.code + '/update'">Update</a>
                    <a class="btn btn-danger" @click.prevent="deleteCourse(data.item.code)">Delete</a>
                </template>
            </b-table>
            <nuxt-link :to="{name: 'courses-create'}">
                <button class="btn btn-dark">Create a New Course</button>
            </nuxt-link>
        </b-container>
        <br>
    </div>
</template>

<script>
export default {
    data () {
        return {
            fieldsStudents: ['username', 'name', 'email', 'password', 'courseCode'],
            students: [],
            fieldsCourses: ['code', 'name', 'buttons'],
            courses: []
        }
    },
    created () {
        this.$axios.$get('/api/students').then((students) => {
            this.students = students
        })

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

            this.$axios.$get('/api/students').then((students) => {
                this.students = []
                this.students.push(...students)
            })
        }
    }
}
</script>
