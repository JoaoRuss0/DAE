<template>
    <div>
        <b-container fluid="sm">
            <div v-if="subject">
                <h1 class="mb-4 mt-4">
                    Subject #{{ subject['code'] }}
                </h1>
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col>{{ subject['name'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Code:</strong></b-col>
                    <b-col>{{ subject['code'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Scholar Year:</strong></b-col>
                    <b-col>{{ subject['scholarYear'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Year:</strong></b-col>
                    <b-col>{{ subject['courseYear'] }}</b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Course Code:</strong></b-col>
                    <b-col>{{ subject['courseCode'] }}</b-col>
                </b-row>

                <div v-if="enrolledStudents.length > 0">
                    <h1 class="mb-4 mt-4">
                        Students Enrolled
                    </h1>

                    <ul class="mb-4 mt-4">
                        <template v-for="student in enrolledStudents">
                            <li :key="student['name']">
                                <strong>Name: </strong> {{ student['name'] }}
                                <strong>Email: </strong> {{ student['email'] }}
                            </li>
                        </template>
                    </ul>
                </div>
                <div v-else>
                    <h1 class="mb-4 mt-4 text-danger">
                        No students enrolled in this subject.
                    </h1>
                </div>

                <div class="mb-4 mt-4">
                    <h1>
                        Enroll Student
                    </h1>

                    <div class="mb-2">
                        <select name="username" v-model="usernameToBeEnrolled">
                            <option value="">None</option>
                            <template v-for="student in notEnrolledStudents">
                                <option :value="student.username">{{student.name}}</option>
                            </template>
                        </select>
                    </div>

                    <button class="btn btn-success" @click="enrollStudent">Enroll</button>
                </div>

                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link to="/subjects">
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
            subject: null,
            enrolledStudents: [],
            notEnrolledStudents: [],
            usernameToBeEnrolled: ""
        }
    },
    created () {
        this.$axios.$get('api/subjects/' + this.$route.params.code).then((subject) => {
            this.subject = subject
        })

        this.$axios.$get('api/subjects/' + this.$route.params.code + '/students').then((students) => {
            this.enrolledStudents = students

            this.$axios.$get('api/students').then((students) => {
                this.notEnrolledStudents = students.filter(student => !this.enrolledStudents.find(enrolled => enrolled['username'] == student['username']))
            })
        })
    },
    methods:
    {
        enrollStudent()
        {
            this.$axios.$put('api/subjects/' + this.$route.params.code + '/enroll/' + this.usernameToBeEnrolled).then(async () =>
                await this.$axios.$get('api/subjects/' + this.$route.params.code + '/students').then(async (students) => {
                    this.usernameToBeEnrolled = ""

                    this.enrolledStudents = []
                    this.enrolledStudents.push(...students)


                    await this.$axios.$get('api/students').then((students) => {
                        this.notEnrolledStudents = []
                        this.notEnrolledStudents.push(...students.filter(student => !this.enrolledStudents.find(enrolled => enrolled['username'] == student['username'])))
                    })
                })
            )
        }
    }
}
</script>
