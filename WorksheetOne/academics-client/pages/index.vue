<template>
    <div>
        <b-container>
            <b-table striped over :items="students" :fields="fieldsStudents"/>
        </b-container>

        <br>

        <b-container>
            <b-table striped over :items="courses" :fields="fieldsCourses">
                <template #cell(buttons)="data">
                    <!--<button type="button" class="btn btn-success" @click="viewCourse(data.item.code)">View</button>
                    <button type="button" class="btn btn-danger" @click="deleteCourse(data.item.code)">Delete</button>-->
                    <a class="btn btn-primary" :href="'api/' + data.item.code">View</a>
                    <a class="btn btn-danger" :href="'api/course/' + data.item.code">Delete</a>
                </template>
            </b-table>
        </b-container>
        <nuxt-link to="/create">Create a New Student</nuxt-link>
    </div>
</template>

<script>
export default {
    data () {
        return {
            fieldsStudents: ['username', 'name', 'email', 'password'],
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
        /*viewCourse: function(value)
        {
            alert(value)
        }*/
    }
}
</script>
