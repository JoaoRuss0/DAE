<template>
    <div>
        <b-container>
            <b-table striped over :items="students" :fields="fields">
                <template #cell(buttons)="data">
                    <a class="btn btn-primary" :href="'students/' + data.item.username">View</a>
                    <nuxt-link :to="'students/' + data.item.username + '/send-email/'">
                        <button class="btn btn-success">Send Email</button>
                    </nuxt-link>
                </template>
            </b-table>
            <nuxt-link :to="{name: 'students-create'}">
                <button class="btn btn-dark">Create a New Student</button>
            </nuxt-link>
            <nuxt-link to="/">
                <button class="btn btn-warning">Return</button>
            </nuxt-link>
        </b-container>
        <br>
    </div>
</template>

<script>
export default {
    data () {
        return {
            fields: ['username', 'name', 'email', 'password', 'courseCode', 'buttons'],
            students: []
        }
    },
    created () {
        this.$axios.$get('/api/students').then((students) => {
            this.students = students
        })
    }
}
</script>
