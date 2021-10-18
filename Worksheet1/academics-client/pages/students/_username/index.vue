<template>
    <div>
        <b-container fluid="sm">
            <div v-if="student && subjects">
                <h1 class="mb-4 mt-4">
                    Student "{{ student['username'] }}"
                </h1>
                <b-row class="mb-3">
                    <b-col><strong>Username:</strong></b-col> <b-col>{{ student['username'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col> <b-col>{{ student['name'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Password:</strong></b-col> <b-col>{{ student['password'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Email:</strong></b-col> <b-col>{{ student['email'] }}</b-col>
                </b-row>

                <h3 class="mb-4 mt-4">
                    Student's Course:
                </h3>
                <b-row class="mb-3">
                    <b-col><strong>Code:</strong></b-col> <b-col>{{ student['courseCode'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col> <b-col>{{ student['courseName'] }}</b-col>
                </b-row>

                <div v-if="subjects.length > 0">
                    <h3 class="mb-4 mt-4">
                        Student's Subjects:
                    </h3>
                    <b-row v-for="subject in subjects" :key="subject['id']" class="mb-3">
                        <b-col><strong>Name:</strong></b-col> <b-col>{{ subject['name'] }}</b-col>
                    </b-row>
                </div>
                <div v-else>
                    <h4 class="mb-4 mt-4 text-danger">Student is enrolled in 0 subjects.</h4>
                </div>

                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link to="/students">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                </b-row>
            </div>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            student: null,
            subjects: null
        }
    },
    created () {
        this.$axios.$get('api/students/' + this.$route.params.username).then((student) => {
            this.student = student
        })

        this.$axios.$get('api/students/' + this.$route.params.username + '/subjects').then((subjects) => {
            this.subjects = subjects
        })
    },
}
</script>
